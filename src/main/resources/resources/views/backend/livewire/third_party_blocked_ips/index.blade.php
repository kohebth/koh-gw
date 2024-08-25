<div>
    <div class="row mt-4">
        <div class="col">
            <div class="form-group row">
                <div class="col-3">
                    {{ html()->label('IP')->class('form-control-label') }}
                    <div>
                        <input type="text" class="form-control my-2" wire:model.defer="filter.ip"/>
                    </div>
                </div>

                <div class="col-3">
                    {{html()->label('Name')->class('form-control-label')}}
                    <div class="pt-2">
                        <select wire:model.defer="filter.name" name="name" class="form-control">
                            <option value=""></option>
                            @foreach($nameOptions as $name)
                                <option value="{{$name}}">{{$name}}</option>
                            @endforeach
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button type="button" title="Search" class='btn btn-info' action-search>
                    <i class="fas fa-search"></i> {{__("Search")}}
                </button>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>
            <div class="form-horizontal content-loading">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>IP</th>
                        <th>Name</th>
                        <th>Created by</th>
                        <th>Created Time</th>
                        <th>Updated by</th>
                        <th>Updated Time</th>
                        <th width="150px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>

                    @if ($blockedIpData->count() > 0)
                        @foreach ($blockedIpData as $item)
                            <tr wire:key="_id-{{ $item->id }}">
                                <td>{{ $item->ip }}</td>
                                <td>{{ $item->name }}</td>
                                <td>{{ $item->createdBy->username ?? '' }}</td>
                                <td>{{ $item->created_at}}</td>
                                <td>{{ $item->updatedBy->username ?? ''}}</td>
                                <td>{{ $item->updated_at }}</td>
                                <td>
                                    @include('backend.includes.action_column.edit-action')
                                    @include('backend.includes.action_column.delete-action')
                                </td>
                            </tr>
                        @endforeach

                    @else
                        <tr><td colspan="12">{{__("No record")}}</td></tr>
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-7">
            <div class="float-left">
                {!! $blockedIpData->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $blockedIpData->links() !!}
            </div>
        </div>
    </div>
</div>

@push('after-scripts')
    <script type="text/javascript">
        $("[action-search]").on('click', function (e) {
        @this.search();
        });
        $( 'body' ).on( 'click', '.js-btn-copy', function () {
            const id = $(this).attr('data-id');

            const copyContent = async () => {
                try {
                    const text = $('.js-btn-copy[data-id=' + id + ']').attr('data-content')
                    await navigator.clipboard.writeText(text);
                } catch (err) {
                    console.error('Failed to copy: ', err);
                }
            }
            setTimeout(copyContent, 1000);
        })
    </script>
@endpush
