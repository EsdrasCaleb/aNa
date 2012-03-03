/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.datatype.ncl.node;

import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.interfaces.NCLSwitchPortPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public abstract class NCLSwitchPrototype<T extends NCLSwitchPrototype,
                                         P extends NCLElement,
                                         I extends NCLElementImpl,
                                         En extends NCLNode,
                                         Ep extends NCLSwitchPortPrototype,
                                         Eb extends NCLSwitchBindRulePrototype>
        extends NCLIdentifiableElementPrototype<En, P, I>
        implements NCLNode<En, P> {

    protected T refer;
    protected En defaultComponent;
    protected IdentifiableElementList<Ep, T> ports;
    protected ElementList<Eb, T> binds;
    protected IdentifiableElementList<En, T> nodes;
    
    protected ItemList<ReferenceType> references;


    /**
     * Construtor do elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch for inválido.
     */
    public NCLSwitchPrototype(String id) throws XMLException {
        super();
        setId(id);
        ports = new IdentifiableElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
        nodes = new IdentifiableElementList<En, T>();
        references = new ItemList<ReferenceType>();
    }


    public NCLSwitchPrototype() throws XMLException {
        super();
        ports = new IdentifiableElementList<Ep, T>();
        binds = new ElementList<Eb, T>();
        nodes = new IdentifiableElementList<En, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Atribui um switch para ser reutilizado pelo switch.
     *
     * @param refer
     *          elemento representando o switch a ser reutilizado.
     */
    public void setRefer(T refer) {
        T aux = this.refer;
        this.refer = refer;
        impl.notifyAltered(NCLElementAttributes.REFER, aux, refer);
    }


    /**
     * Retorna o switch reutilizado pelo switch.
     *
     * @return
     *          elemento representando o switch a ser reutilizado.
     */
    public T getRefer() {
        return refer;
    }


    /**
     * Adiciona uma porta ao contexto.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(Ep port) throws XMLException {
        if(ports.add(port, (T) this)){
            impl.notifyInserted(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(Ep port) throws XMLException {
        if(ports.remove(port)){
            impl.notifyRemoved(NCLElementSets.PORTS, port);
            return true;
        }
        return false;
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) throws XMLException {
        if(ports.remove(id)){
            impl.notifyRemoved(NCLElementSets.PORTS, id);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(Ep port) throws XMLException {
        return ports.contains(port);
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) throws XMLException {
        return ports.get(id) != null;
    }


    /**
     * Verifica se o contexto possui alguma porta.
     *
     * @return
     *          verdadeiro se o contexto possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Retorna as portas do contexto.
     *
     * @return
     *          lista contendo as portas do contexto.
     */
    public IdentifiableElementList<Ep, T> getPorts() {
        return ports;
    }


    /**
     * Determina o componente padrão do switch.
     *
     * @param defaultComponent
     *          elemento representando o componente padrão.
     */
    public void setDefaultComponent(En defaultComponent) {
        if(this.defaultComponent != null)
            impl.notifyRemoved(NCLElementSets.DEFAULTCOMPONENT, this.defaultComponent);
        
        this.defaultComponent = defaultComponent;
        
        if(this.defaultComponent != null)
            impl.notifyInserted(NCLElementSets.DEFAULTCOMPONENT, this.defaultComponent);
    }


    /**
     * Retorna o componente padrão do switch.
     *
     * @return
     *          elemento representando o componente padrão.
     */
    public En getDefaultComponent() {
        return defaultComponent;
    }


    /**
     * Adiciona um bind ao switch.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(Eb bind) throws XMLException {
        if(binds.add(bind, (T) this)){
            impl.notifyInserted(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Remove um bind do switch.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(Eb bind) throws XMLException {
        if(binds.remove(bind)){
            impl.notifyRemoved(NCLElementSets.BINDS, bind);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch possui algum bind.
     *
     * @return
     *          verdadeiro se o switch possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch.
     *
     * @return
     *          lista contendo os binds do switch.
     */
    public ElementList<Eb, T> getBinds() {
        return binds;
    }


    /**
     * Adiciona um nó ao contexto.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(En node) throws XMLException {
        if(nodes.add(node, (T) this)){
            impl.notifyInserted(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    /**
     * Remove um nó do contexto.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(En node) throws XMLException {
        if(nodes.remove(node)){
            impl.notifyRemoved(NCLElementSets.NODES, node);
            return true;
        }
        return false;
    }


    /**
     * Remove um nó do contexto.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(String id) throws XMLException {
        if(nodes.remove(id)){
            impl.notifyRemoved(NCLElementSets.NODES, id);
            return true;
        }
        return false;
    }


    /**
     * Verifica se o contexto possui um nó.
     *
     * @param node
     *          elemento representando o nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(En node) throws XMLException {
        return nodes.contains(node);
    }


    /**
     * Verifica se o contexto possui um nó.
     *
     * @param id
     *          identificador do nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(String id) throws XMLException {
        return nodes.get(id) != null;
    }


    /**
     * Verifica se o contexto possui algum nó.
     *
     * @return
     *          verdadeiro se o contexto possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Retorna os nós do contexto.
     *
     * @return
     *          lista contendo os nós do contexto.
     */
    public IdentifiableElementList<En, T> getNodes() {
        return nodes;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
